package graphqlFetcher

import graphql.schema.DataFetcher

//class the builds mapping for the dataFetchers
class DataFetcherBuilder {

    private val userFetcher = UserFetcher()

    fun buildQueryDataFetchers(): Map<String, DataFetcher<*>?> {
        val queryDataFetchers = HashMap<String, DataFetcher<*>?>()
        queryDataFetchers["users"] = userFetcher.getAllUsers()
        queryDataFetchers["userById"] = userFetcher.getUserById()
        queryDataFetchers["userByUsername"] = userFetcher.getUserByUsername()
        return queryDataFetchers
    }

    fun buildMutationDataFetcher(): Map<String, DataFetcher<*>?> {
        val mutationDataFetcher = HashMap<String, DataFetcher<*>?>()
        mutationDataFetcher["createUser"] = userFetcher.createUser()
        mutationDataFetcher["updateUser"] = userFetcher.updateUser()
        mutationDataFetcher["deleteUser"] = userFetcher.deleteUser()
        return mutationDataFetcher
    }
}