package dev.vladimir.mobinttestproject.data.mappers

import android.graphics.Color
import dev.vladimir.mobinttestproject.data.response.all_companies.CompanyResponseModel
import dev.vladimir.mobinttestproject.domain.models.Company
import javax.inject.Inject

class CompaniesMapper @Inject constructor() {

    fun mapCompanies(companiesResponse: List<CompanyResponseModel>): List<Company> =
        companiesResponse.map(::mapCompany)

    private fun mapCompany(companyResponseModel: CompanyResponseModel): Company =
        with(companyResponseModel) {
            Company(
                id = company.companyId,
                companyName = mobileAppDashboard.companyName,
                logoUrl = mobileAppDashboard.logo,
                backgroundColor = Color.parseColor(mobileAppDashboard.cardBackgroundColor),
                mainColor = Color.parseColor(mobileAppDashboard.mainColor),
                cardBackgroundColor = Color.parseColor(mobileAppDashboard.cardBackgroundColor),
                textColor = Color.parseColor(mobileAppDashboard.textColor),
                highlightTextColor = Color.parseColor(mobileAppDashboard.highlightTextColor),
                accentColor = Color.parseColor(mobileAppDashboard.accentColor),
                mark = customerMarkParameters.mark,
                markToCash = customerMarkParameters.loyaltyLevel.markToCash,
                levelName = customerMarkParameters.loyaltyLevel.name
            )
        }
}
