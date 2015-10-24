#!/usr/bin/env python

import boto3
from boto3.dynamodb.conditions import Key

def connect():
	AWS_REGION = 'us-east-1'
	db = boto3.resource('dynamodb', region_name = AWS_REGION)	
	return db






                    
table = db.Table('2012')
data = table.query(KeyConditionExpression = Key('Ticker').eq('AAPL US Equity') & Key('Date').between('2012-01-01', '2012-01-04'))


for d in data['Items']:
	print(d)