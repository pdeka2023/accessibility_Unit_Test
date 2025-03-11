package axe.my.support;

import freemarker.template.Template;


import java.io.IOException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import axe.my.support.a11y.Engine;
import axe.my.support.ftl.FtlConfig;
import axe.my.support.modal.axe.Issues;
import axe.my.support.util.A11y;

public interface IRunner {
    Object execute() throws IOException;

    default void generateHtmlReport(A11y a11y, Engine engine, Class<?> clazz) throws IOException {
        Template tmplIndex = FtlConfig.getInstance().getTemplate(engine.name().toLowerCase() + "/index.ftl");
        Template tmplPage = FtlConfig.getInstance().getTemplate(engine.name().toLowerCase() + "/page.ftl");
        List<?> issuesList = a11y.jsonReports(engine, clazz);

        issuesList.forEach(issues -> {
            String id = engine.name().equalsIgnoreCase("axe") ? ((Issues) issues).getId() : ((axe.my.support.modal.htmlcs.Issues) issues).getId();
            a11y.save(tmplPage, issues, id, engine);
        });

        Map<String, Object> root = new HashMap<>();
        root.put("list", issuesList);
        root.put("title", "Accessibility Report");
        a11y.save(tmplIndex, root, "index", engine);
    }
}
