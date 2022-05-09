from gc import callbacks
from lib2to3.pgen2.token import NT_OFFSET
import scrapy
import time


class SubSpider(scrapy.Spider):
    name = 'subSpider'
    start_urls = ['https://www.submarino.com.br/busca/notebook-gamer', 'https://www.submarino.com.br/busca/notebook-gamer?limit=24&offset=24']
    
    def parse(self, response):
      z = 0
      for products in response.css('div+div a[href*=produto]::attr(href)').get():
          time.sleep(3)
          rfollow = 'https://www.submarino.com.br'+response.css('div+div a[href*=produto]::attr(href)')[z].get()
          z += 1
          yield response.follow(rfollow, callback=self.parse_notebooks)
          
    def parse_notebooks(self, response):
        campos = response.css('td.src__Text-sc-10qje1m-4::text').getall()
        
        y = 0
        marca = -1
        processador = -1
        ram = -1
        so = -1
        placa = -1
        so = -1
        hd = -1
        ssd = -1
        modelo = -1
        
        for x in campos:
            if x == 'Marca':
                marca = y + 1
            if x == 'Fabricante':
                marca = y + 1
            elif x == 'ModeloProcessador':
                processador = y + 1
            elif x == 'Memória Ram':
                ram = y + 1
            elif x == 'PlacaDeVideoNote':
                placa = y + 1
            elif x == 'Cartão de Vídeo':
                placa = y + 1
            elif x == 'Sistema Operacional':
                so = y + 1
            elif x == 'SistemaOperacionalCPU':
                so = y + 1
            elif x == 'HD':
                hd = y + 1
            elif x == 'SSD':
                ssd = y + 1
            elif x == 'SsdNote':
                ssd = y + 1
            elif x =='Referência do Modelo':
                modelo = y + 1
            elif x =='Modelo':
                modelo = y + 1
            y += 1
        
            
        if marca == -1:
            marcatxt = 'NT/NI'
        else:
            marcatxt = response.css('td.src__Text-sc-10qje1m-4::text')[marca].get()
        if processador == -1:
            processadortxt = 'NT/NI'
        else:
            processadortxt = response.css('td.src__Text-sc-10qje1m-4::text')[processador].get()
        if ram == -1:
            ramtxt = 'NT/NI'
        else:
            ramtxt = response.css('td.src__Text-sc-10qje1m-4::text')[ram].get()
        if so == -1:
            sotxt = 'NT/NI'
        else:
            sotxt = response.css('td.src__Text-sc-10qje1m-4::text')[so].get()
        if placa == -1:
            placatxt = 'NT/NI'
        else:
            placatxt = response.css('td.src__Text-sc-10qje1m-4::text')[placa].get()
        if hd == -1:
            hdtxt = 'NT/NI'
        else:
            hdtxt = response.css('td.src__Text-sc-10qje1m-4::text')[hd].get()
        if ssd == -1:
            ssdtxt = 'NT/NI'
        else:
            ssdtxt = response.css('td.src__Text-sc-10qje1m-4::text')[ssd].get()
        if modelo == -1:
            modelotxt = 'NT/NI'
        else:
            modelotxt = response.css('td.src__Text-sc-10qje1m-4::text')[modelo].get()
            
        precotxt = response.css('span.src__ListPrice-sc-1jnodg3-2::text')[1].get()
        
        if precotxt == None:
            precotxt =  response.css('div[class*=priceSales]::text')[1].get()
        
        yield{
            'name' : response.css('h1::text').get(),
            'modelo' : modelotxt,
            'preco' : precotxt,
            'marca' : marcatxt,
            'processador' : processadortxt,
            'ram' : ramtxt,
            'so' : sotxt,
            'placa' : placatxt,
            'hd' : hdtxt,
            'ssd' : ssdtxt,
            'nota' : response.css('span.header__RatingValue-sc-1o3gjvp-9::text').get(),
            'avaliadores' : response.css('div.review__Flex-l45my2-0::text').getall(),
            'comentarios' : response.css('span.src__Text-sc-154pg0p-0::text').getall()
        }
            