/*
 * !++
 * QDS - Quick Data Signalling Library
 * !-
 * Copyright (C) 2002 - 2020 Devexperts LLC
 * !-
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0.
 * If a copy of the MPL was not distributed with this file, You can obtain one at
 * http://mozilla.org/MPL/2.0/.
 * !__
 */
package com.dxfeed.api.codegen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

class ClassGen {
    private static final String PACKAGE_TMPL_VAR = "PACKAGE";
    private static final String CLASS_NAME_TMPL_VAR = "CLASS_NAME";

    private static final Pattern IMPORT_PATTERN = Pattern.compile("import ([\\w.*]+);.*");
    private static final String BEGIN_MARKER = "// BEGIN: CODE AUTOMATICALLY GENERATED";

    private static final String END_MARKER = "// END: CODE AUTOMATICALLY GENERATED";
    private static final String REGEN_NOTICE = ": DO NOT MODIFY. IT IS REGENERATED BY " + ImplCodeGen.class.getName();

    private final ClassName className;
    private final List<String> preImports;
    private final Set<ClassName> importedClasses;
    private final List<String> header;
    private final List<String> code = new ArrayList<>();
    private final List<String> footer;
    private final Map<String, String> variables = new HashMap<>();

    private int indent = 1;

    private ClassGen(ClassName className, List<String> preImports, Set<ClassName> importedClasses, List<String> header,
        List<String> footer)
    {
        this.className = className;
        this.preImports = preImports;
        this.importedClasses = importedClasses;
        this.header = header;
        this.footer = footer;

        variables.put(PACKAGE_TMPL_VAR, className.getPackageName());
        variables.put(CLASS_NAME_TMPL_VAR, className.getSimpleName());
    }

    private static ClassGen readTemplate(ClassName target, BufferedReader reader) {
        Iterator<String> lineIterator = reader.lines().iterator();
        List<String> preImports = new ArrayList<>();
        Set<ClassName> importedClasses = new HashSet<>();
        List<String> header = new ArrayList<>();
        List<String> footer = new ArrayList<>();

        boolean importsStarted = false;

        while (lineIterator.hasNext()) {
            String line = lineIterator.next();
            if (line.trim().startsWith(BEGIN_MARKER))
                break;
            Matcher importMatcher = IMPORT_PATTERN.matcher(line);
            if (importMatcher.matches()) {
                importsStarted = true;
                importedClasses.add(new ClassName(importMatcher.group(1)));
                header.clear();
            } else {
                if (importsStarted) {
                    header.add(line);
                } else {
                    preImports.add(line);
                }
            }
        }
        if (!importsStarted)
            throw new IllegalArgumentException("Source file does not have any import statements");
        if (!lineIterator.hasNext())
            throw new IllegalArgumentException("Source file does not have BEGIN marker for generated code");
        while (lineIterator.hasNext()) {
            String line = lineIterator.next();
            if (line.trim().startsWith(END_MARKER))
                break;
            // skip code between BEGIN and END markers
        }
        if (!lineIterator.hasNext())
            throw new IllegalArgumentException("Source file does not have END marker for generated code");
        while (lineIterator.hasNext()) {
            String line = lineIterator.next();
            footer.add(line);
        }
        return new ClassGen(target, preImports, importedClasses, header, footer);
    }

    static ClassGen resolve(ClassName target, String templateName, CodeGenEnvironment env) throws IOException {
        if (env.hasSourceFile(target)) {
            try (BufferedReader reader = env.openSourceFile(target)) {
                return readTemplate(target, reader);
            }
        } else {
            try (BufferedReader reader =
                new BufferedReader(new InputStreamReader(ClassGen.class.getResourceAsStream(templateName))))
            {
                return readTemplate(target, reader);
            }
        }
    }

    void setVariableValue(String variableName, String value) {
        if (CLASS_NAME_TMPL_VAR.equals(variableName) || PACKAGE_TMPL_VAR.equals(variableName))
            throw new IllegalArgumentException("Cannot modify package or class name variables");
        variables.put(variableName, value);
    }

    void addImport(ClassName className) {
        if (importedClasses.contains(className) ||
            importedClasses.contains(new ClassName(className.getPackageName(), "*")))
        {
            return;
        }
        if (className.getPackageName().equals(this.className.getPackageName()))
            return;
        importedClasses.add(className);
    }

    void indent() {
        indent++;
    }

    void unindent() {
        if (indent <= 1)
            throw new IllegalStateException("Indentation underflow");
        indent--;
    }

    void code(String line) {
        if (line.length() == 0) {
            code.add("");
            return;
        }
        String[] split = line.split("\n");
        for (int i = 0; i < split.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < indent; j++) {
                sb.append("    ");
            }
            if (i > 0)
                sb.append("    "); // continuation
            sb.append(split[i]);
            code.add(sb.toString());
        }
    }

    void newLine() {
        code("");
    }

    private void processImports(List<String> lines) {
        Predicate<ClassName> javaPackage =
            name -> name.getPackageName().startsWith("java.") || name.getPackageName().startsWith("javax.");
        Function<ClassName, String> toImportString = name -> "import " + name + ";";
        importedClasses.stream()
            .filter(javaPackage.negate())
            .map(toImportString)
            .sorted().forEach(lines::add);
        if (importedClasses.stream().anyMatch(javaPackage.negate()) && importedClasses.stream().anyMatch(javaPackage))
            lines.add("");
        importedClasses.stream()
            .filter(javaPackage)
            .map(toImportString)
            .sorted().forEach(lines::add);
    }

    List<String> buildSource() {
        List<String> lines = new ArrayList<>();
        lines.addAll(preImports);
        processImports(lines);
        lines.addAll(header);
        lines.add(BEGIN_MARKER + REGEN_NOTICE);
        lines.addAll(code);
        lines.add(END_MARKER);
        lines.addAll(footer);

        Pattern pattern = Pattern.compile(variables.keySet().stream()
            .map(var1 -> Pattern.quote("{{" + var1 + "}}"))
            .collect(Collectors.joining("|")));
        lines.replaceAll(line -> {
            Matcher matcher = pattern.matcher(line);
            StringBuffer result = new StringBuffer();
            while (matcher.find()) {
                String var = matcher.group();
                matcher.appendReplacement(result, variables.get(var.substring(2, var.length() - 2)));
            }
            matcher.appendTail(result);
            return result.toString();
        });
        return lines;
    }
}
