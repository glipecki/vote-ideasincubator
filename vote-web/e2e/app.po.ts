import {browser, element, by} from 'protractor';

export class VoteWebPage {
  navigateTo() {
    return browser.get('/');
  }

  getParagraphText() {
    return element(by.css('vii-root h1')).getText();
  }
}
