'''
Created on 7 nov. 2013

@author: Alexandre Bonhomme
'''
from fr.redteam.zarascraper.core.downloader import Downloader

class Product(object):
    '''
    Class which describe a product
    '''

    def __init__(self, modelName, brandName, color, imgUrl, imgPath, productType, bodyPart):
        self.id = None
        self.model = modelName
        self.brand = brandName
        self.color = color
        self.imgUrl = imgUrl
        self.imgPath = imgPath
        self.type = productType
        self.bodies = bodyPart
        self.weatherList = []

    def getImage(self):
        return Downloader().getFile(self.imgUrl)

    def toString(self):
        return str('<' +
              self.brand + '><' +
              self.model + '><' +
              self.color['name'] + '><' +
              self.type + '><' +
              self.bodies + '><' +
              self.imgUrl + '><' +
              self.imgPath + '>')
