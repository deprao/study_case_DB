from gc import callbacks
from lib2to3.pgen2.token import NT_OFFSET
import scrapy
import time

class MagaSpider(scrapy.Spider):
    name = 'maga'
    start_urls = ['https://www.magazineluiza.com.br/notebook-gamer/informatica/s/in/ntbg?page=1', 'https://www.magazineluiza.com.br/notebook-gamer/informatica/s/in/ntbg?page=2', 'https://www.magazineluiza.com.br/notebook-gamer/informatica/s/in/ntbg?page=3']
    
    def parse(self, response):
      
      for products in response.css('div+ ul a[href*=notebook-gamer]::attr(href)'):
          time.sleep(3)
          yield response.follow(products.get(), callback=self.parse_notebooks)
          
    def parse_notebooks(self, response):
        campos = response.css('td.description__information-left::text').getall()
        campos_1 = response.css('td.description__information-box-left::text').getall()
        
        y = 0
        z = 0
        marca = -1
        processador = -1
        ram = -1
        so = -1
        placa = -1
        so = -1
        hd = -1
        ssd = -1
        
        for x in campos:
            if x == 'Marca':
                marca = y
            elif x == 'Processador':
                processador = y
            elif x == 'Memória RAM':
                ram = y
            elif x == 'Especificações da placa de vídeo - Modelo':
                placa = y
            elif x == 'Sistema operacional':
                so = y
            elif x == 'Capacidade do HD':
                hd = y
            elif x == 'Capacidade do SSD':
                ssd = y
            y += 1
        
        for x in campos_1:
            if x.strip() == 'Marca':
                marca = z
            elif x == '  Processador ':
                processador = z
            elif x == '  Memória RAM ':
                ram = z
            elif x == '  Placa de vídeo ':
                placa = z
            elif x == '  Sistema Operacional ':
                so = z
            elif x == '  Capacidade do HD ':
                hd = z
            elif x == '  Capacidade do SSD ':
                ssd = z
            z += 1
            
        if marca == -1:
            marcatxt = 'NT/NI'
        else:
            marcatxt = response.css('td.description__information-box-right::text')[marca].get().strip()
        if processador == -1:
            processadortxt = 'NT/NI'
        else:
            processadortxt = response.css('td.description__information-box-right::text')[processador].get().strip()
        if ram == -1:
            ramtxt = 'NT/NI'
        else:
            ramtxt = response.css('td.description__information-box-right::text')[ram].get().strip()
        if so == -1:
            sotxt = 'NT/NI'
        else:
            sotxt = response.css('td.description__information-box-right::text')[so].get().strip()
        if placa == -1:
            placatxt = 'NT/NI'
        else:
            placatxt = response.css('td.description__information-box-right::text')[placa].get().strip()
        if hd == -1:
            hdtxt = 'NT/NI'
        else:
            hdtxt = response.css('td.description__information-box-right::text')[hd].get().strip()
        if ssd == -1:
            ssdtxt = 'NT/NI'
        else:
            ssdtxt = response.css('td.description__information-box-right::text')[ssd].get().strip()
            
        precotxt = response.css('div.price-template__from::text').get()
        
        if precotxt == None:
            precotxt = response.css('span.price-template__text::text').get()
        
        yield{
            'name' : response.css('h1::text').get(),
            'preco' : precotxt,
            'marca' : marcatxt,
            'processador' : processadortxt,
            'ram' : ramtxt,
            'so' : sotxt,
            'placa' : placatxt,
            'hd' : hdtxt,
            'ssd' : ssdtxt,
            'nota' : response.css('span.product-review__rating-average::text').get(),
            'avaliadores' : response.css('span.product-review__text-content::text').getall(),
            'comentarios' : response.css('p.product-review__text-content::text').getall()
        }
            