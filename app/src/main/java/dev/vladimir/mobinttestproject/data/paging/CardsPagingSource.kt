package dev.vladimir.mobinttestproject.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.vladimir.mobinttestproject.domain.models.Company
import java.lang.Exception

class CardsPagingSource(
    private val loader: suspend (offset: Int) -> List<Company>,
) : PagingSource<Int, Company>() {

    override fun getRefreshKey(state: PagingState<Int, Company>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Company> {
        val offset = params.key ?: 0

        return try {
            val companies = loader.invoke(offset)

            return LoadResult.Page(
                data = companies,
                prevKey = if (offset == 0) null else offset - 1,
                nextKey = if (companies.size < params.loadSize) null else offset + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(throwable = e)
        }
    }
}
