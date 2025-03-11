package axe.my.support;

import org.openqa.selenium.WebDriver;

import axe.my.support.a11y.AxeTag;
import axe.my.support.a11y.Engine;
import axe.my.support.modal.axe.Issues;
import axe.my.support.modal.htmlcs.Params;
import axe.my.support.util.A11y;

import java.io.IOException;

public class AxeRunner implements IRunner {

    private A11y a11y;
    private Params params;

    public AxeRunner(WebDriver driver) {
        a11y = new A11y(driver);
        params = new Params();
    }

    public AxeRunner setPageTile(String pageTitle) {
        params.setPageTitle(pageTitle);
        return this;
    }

    public AxeRunner setTags(AxeTag... tag) {
        params.setTags(tag);
        return this;
    }

    public AxeRunner disableRules(String... rules) {
        params.disableRules(rules);
        return this;
    }

    public AxeRunner enableRules(String... rules) {
        params.enableRules(rules);
        return this;
    }

    public AxeRunner setScriptURL(String url) {
      params.setScriptURL(url);
      return this;
    }

    @Override
    public Issues execute() throws IOException {
        return (Issues) a11y.execute(Engine.AXE, params);
    }

    public void generateHtmlReport() throws IOException {
        IRunner.super.generateHtmlReport(a11y, Engine.AXE, Issues.class);
    }
}
