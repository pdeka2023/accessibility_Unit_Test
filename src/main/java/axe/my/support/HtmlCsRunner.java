package axe.my.support;

import org.openqa.selenium.WebDriver;

import axe.my.support.a11y.Engine;
import axe.my.support.a11y.HTMLCS;
import axe.my.support.modal.htmlcs.Issues;
import axe.my.support.modal.htmlcs.Params;
import axe.my.support.util.A11y;

import java.io.IOException;
import java.util.Objects;

public class HtmlCsRunner implements IRunner {
  
    private A11y a11y;
    private HTMLCS standard;
    private Params params;
    private String[] codes = {};

    public HtmlCsRunner(WebDriver driver) {
        a11y = new A11y(driver);
        params = new Params();
    }

    public HtmlCsRunner setStandard(HTMLCS standard) {
        this.standard = standard;
        return this;
    }

    public HtmlCsRunner setIgnoreCodes(String[] codes) {
        this.codes = codes;
        return this;
    }

    public HtmlCsRunner setPageTile(String pageTitle) {
        params.setPageTitle(pageTitle);
        return this;
    }

    public HtmlCsRunner setScriptURL(String url) {
      params.setScriptURL(url);
      return this;
    }

    @Override
    public Issues execute() throws IOException {
        String stdrd = Objects.isNull(standard) ? HTMLCS.WCAG2AA.name() : standard.name();
        params.setStandard(stdrd);
        params.setIgnoreCodes(codes);
        return (Issues) a11y.execute(Engine.HTMLCS, params);
    }

    public void generateHtmlReport() throws IOException {
        IRunner.super.generateHtmlReport(a11y, Engine.HTMLCS, Issues.class);
    }
}
