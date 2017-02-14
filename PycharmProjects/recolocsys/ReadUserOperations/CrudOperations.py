import sys
from pymongo import MongoClient
from pymongo.errors import ConnectionFailure


class DataOperations(object):
    __databaseUrl = None
    __databaseName = None
    __DatabaseCol = None

    def __init__(self):
        pass

    def __init__(self, dbUrl, dbName, dbCollection):
        self.databaseUrl = dbUrl
        self.databaseName = dbName
        self.DatabaseCol = dbCollection

    def setDbUrl(self, url):
        self.__databaseUrl = url


    def setDbName(self, dbName):
        self.__databaseName = dbName

    def getDbname(self, ):
        return self.__databaseName

    def setCollName(self, collName):
        self.__DatabaseCol = collName

    def getCollName(self):
        return self.__DatabaseCol

    def connectDatabase(self):
        try:
            clientConn = MongoClient(self.__databaseUrl)
            print("Connection Established Successfully")
            db = clientConn[self.getDbname()]
            db_coll = db[self.getCollName()]
            return db_coll

        except  ConnectionFailure as e:
            sys.stderr.write('Could not connec to MongoDb:  %s', e)

    def insertRecToUser(self, query):
        usersCollection = self.connectDatabase()
        usersCollection.insert(query)
        print("inserting done")

    def updateUserRec(self, query):
        usersColl = self.connectDatabase()
        usersColl.update(query)
        print("updating done")

    def removeUserRec(self, query):
        usersColl = self.connectDatabase()
        usersColl.remove(query)
        print("removing done")

    def findUserRec(self, query):
        usersColl = self.connectDatabase()
        doc = usersColl.find(query)
        return doc
