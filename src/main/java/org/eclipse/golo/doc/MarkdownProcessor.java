/*
 * Copyright (c) 2012-2017 Institut National des Sciences Appliquées de Lyon (INSA-Lyon)
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.golo.doc;

import gololang.FunctionReference;
import gololang.IO;

import java.nio.file.Path;
import java.util.Map;

public class MarkdownProcessor extends AbstractProcessor {

  @Override
  protected String fileExtension() {
    return "markdown";
  }

  @Override
  public String render(ModuleDocumentation documentation) throws Throwable {
    FunctionReference template = template("template", fileExtension());
    addModule(documentation);
    return (String) template.invoke(documentation);
  }

  @Override
  public void process(Map<String, ModuleDocumentation> modules, Path targetFolder) throws Throwable {
    setTargetFolder(targetFolder);
    for (ModuleDocumentation doc : modules.values()) {
      IO.textToFile(render(doc), outputFile(doc.moduleName()));
    }
    renderIndex("index");
  }
}
