import csv
import pprint
from pymongo import MongoClient

mongo = MongoClient('mongodb://admin:guc3XK9drxmH3vhZ@sd-shard-00-00-gmjiq.mongodb.net:27017,sd-shard-00-01-gmjiq.mongodb.net:27017,sd-shard-00-02-gmjiq.mongodb.net:27017/test?ssl=true&replicaSet=SD-shard-0&authSource=admin&retryWrites=true')
universities = mongo.test.university
courses = mongo.test.course

# Load KISAIM labels
kisaim_labels = {}
with open("Unistats/KISAIM.csv", "r", newline="") as file:
    reader = csv.reader(file, delimiter=',')
    next(reader) # Skip Header
    for row in reader:
        kisaim_labels[row[0]] = row[1]

# Import universities
def import_universities():
    with open("Unistats/University Lookup.csv", "r", newline="") as file:
        reader = csv.reader(file, delimiter=';')
        next(reader) # Skip Header
        for row in reader:
            if row[0] == "":
                continue;
            ukprn = int(row[0])
            name = row[1]
            universities.update_one({'ukprn': ukprn}, {'$set': {
                'ukprn': ukprn,
                'name': name
            }}, True)
            courses.update({'pubukprn': ukprn}, {'$set': {
                'university.name': name
            }}, multi=True)
            print(ukprn, name)

# Import institution
def import_institution():
    with open("Unistats/INSTITUTION.csv", "r", newline="") as file:
        reader = csv.reader(file, delimiter=',')
        next(reader) # Skip Header
        for row in reader:
            pubukprn = int(row[0])
            rating = row[4].lower()
            if rating == "bronze":
                rating = 1
            elif rating == "silver":
                rating = 2
            elif rating == "gold":
                rating = 3
            else:
                rating = 0
            universities.update_one({'ukprn': pubukprn}, {
                '$set': {
                    'teachingExcellence': rating
                }
            }, True)
            courses.update({'pubukprn': pubukprn}, {'$set': {
                'university.teachingExcellence': rating
            }}, multi=True)
            print(pubukprn, rating)

# Import courses
def import_courses():    
    with open("Unistats/KISCOURSE.csv", "r", newline="") as file:
        reader = csv.reader(file, delimiter=',')
        next(reader) # Skip Header
        for row in reader:
            pubukprn = int(row[0])
            courseid = row[16]
            mode = int(row[17])
            title = row[29]
            kisaim_code = row[34]
            url = row[4]
            courses.update_one({
                'pubukprn': pubukprn,
                'courseid': courseid,
                'mode': mode
            }, {
                '$set': {
                    'pubukprn': pubukprn,
                    'courseid': courseid,
                    'mode': mode,
                    'title': title,
                    'academicTitle': kisaim_labels[kisaim_code],
                    'url': url
                }
            }, True)
            print(pubukprn, courseid, title)

# Import employment
def import_employment():
    with open("Unistats/EMPLOYMENT.csv", "r", newline="") as file:
        reader = csv.reader(file, delimiter=',')
        next(reader) # Skip Header
        for row in reader:
            pubukprn = int(row[0])
            courseid = row[2]
            mode = int(row[3])
            if row[14] == "":
                employmentChances = 0
            else:
                employmentChances = int(row[14])
            courses.update_one({
                'pubukprn': pubukprn,
                'courseid': courseid,
                'mode': mode
            }, {
                '$set': {
                    'employmentChances': employmentChances
                }
            }, True)
            print(pubukprn, courseid, mode, employmentChances)

# Import NSS
def import_nss():
    with open("Unistats/NSS.csv", "r", newline="") as file:
        reader = csv.reader(file, delimiter=',')
        next(reader) # Skip Header
        for row in reader:
            pubukprn = int(row[0])
            courseid = row[2]
            mode = int(row[3])
            if row[29] == "":
                qualityOfLife = 0
            else:
                qualityOfLife = int(row[29])

            courses.update_one({
                'pubukprn': pubukprn,
                'courseid': courseid,
                'mode': mode
            }, {
                '$set': {
                    'qualityOfLife': qualityOfLife
                }
            }, True)
            print(pubukprn, courseid, mode, qualityOfLife)

# Import degree class
def import_degree():
    with open("Unistats/DEGREECLASS.csv", "r", newline="") as file:
        reader = csv.reader(file, delimiter=',')
        next(reader) # Skip Header
        for row in reader:
            pubukprn = int(row[0])
            courseid = row[2]
            mode = int(row[3])
            if row[8] == "":
                firstClassRate = 0
            else:
                firstClassRate = int(row[8])

            courses.update_one({
                'pubukprn': pubukprn,
                'courseid': courseid,
                'mode': mode
            }, {
                '$set': {
                    'firstClassRate': firstClassRate
                }
            }, True)
            print(pubukprn, courseid, mode, firstClassRate)

def set_rating():
    cursor = courses.find({})
    for course in cursor:
        rating = (1 + course["employmentChances"]/100) * (1 + course["qualityOfLife"]/100) * (1 + course["firstClassRate"]/100) * (1 + course["university"]["teachingExcellence"] / 10)
        rating = (rating / 16) * 100
        courses.update_one({'_id': course['_id']}, { '$set': {
            'rating': rating
        }})
        print(course['pubukprn'], rating)

#import_universities()
#import_institution()
#import_courses()
#import_employment()
#import_nss()
#import_degree()
#set_rating()
