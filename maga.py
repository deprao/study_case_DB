import scrapy


class MagaSpider(scrapy.Spider):
    name = 'maga'
    allowed_domains = ['magazineluiza.com.br']
    start_urls = ['https://www.magazineluiza.com.br/notebook-gamer/informatica/s/in/ntbg/']

    def parse(self, response, **kwargs):
        for i in response.xpath('//a[@name="linkToProduct"]'):  
            link = ('@href').get()
        pass

        yield{
            'link' : link
        }