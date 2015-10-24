#!/usr/bin/env python

import boto3
from boto3.dynamodb.conditions import Key



class Company():
	def __init__(self, name):
		self.name = name
		self.values = {}

	def __str__(self):
		s = str(self.name)
		for month in self.values:
			s+= str(month) + " " + str(self.values[month])
		return s


def connect():
	AWS_REGION = 'us-east-1'
	db = boto3.resource('dynamodb', region_name = AWS_REGION)	
	return db


def grabData(company, startDate, endDate, year, db):
	table = db.Table(str(year))
	startDate = str(year) + '-' + startDate 
	endDate = str(year) + '-' + endDate
	data = table.query(KeyConditionExpression = Key('Ticker').eq(company.name) & Key('Date').between(startDate, endDate))
	return data['Items']

def extractMonth(data):
	for d in data:
		print( str(d['Date'])  + ": " + str(d['Open']) )

def pullYears(company, startYear, endYear, db):
	if endYear < startYear:
		tmp = endYear
		endYear = startYear
		startYear = endYear
	
	for year in range(startYear, endYear+1):
		extractMonth(grabData(company, '01-01', '12-31', year, db))

if __name__ == "__main__":
	arr = [Company('AAPL US Equity')]
	db = connect()
	for company in arr:
		pullYears(company, 2008, 2012, db)
	