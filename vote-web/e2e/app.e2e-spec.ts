import { VoteWebPage } from './app.po';

describe('vote-web App', function() {
  let page: VoteWebPage;

  beforeEach(() => {
    page = new VoteWebPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('vii works!');
  });
});
