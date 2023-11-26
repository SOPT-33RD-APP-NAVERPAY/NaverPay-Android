package com.dosopt.naverpay.ui.main.benefit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dosopt.naverpay.R
import com.dosopt.naverpay.domain.model.benefit.BenefitBrand
import com.dosopt.naverpay.domain.model.benefit.BenefitUser

class BenefitViewModel : ViewModel() {
    val cardImages = MutableLiveData(
        listOf(
            R.drawable.img_benefit_btn_card_1,
            R.drawable.img_benefit_btn_card_2,
            R.drawable.img_benefit_btn_card_3,
            R.drawable.img_benefit_btn_card_3
        )
    )

    val admobImages = MutableLiveData(
        listOf(
            R.drawable.img_ad_pica,
            R.drawable.img_ad_tj,
            R.drawable.img_ad_paris,
        )
    )

    val mockUserInfo = MutableLiveData(
        BenefitUser(
            "서재원",
            13000
        )
    )

    val mockPopularBrands = MutableLiveData(
        listOf(
            BenefitBrand(
                id = 1,
                name = "스타벅스",
                logo_img_url = "https://github.com/librarywon/Codelab_Compose/assets/52442547/da0ecd21-d35b-4533-8173-afeb81cdc6f3",
                discount_content = "스타벅스 카드 10% 할인",
                discount_type = "카드할인",
                is_brand_like = true
            ),
            BenefitBrand(
                id = 2,
                name = "스타벅스",
                logo_img_url = "https://github.com/librarywon/Codelab_Compose/assets/52442547/da0ecd21-d35b-4533-8173-afeb81cdc6f3",
                discount_content = "스타벅스 카드 10% 할인",
                discount_type = "카드할인",
                is_brand_like = true
            ),
            BenefitBrand(
                id = 3,
                name = "스타벅스",
                logo_img_url = "https://github.com/librarywon/Codelab_Compose/assets/52442547/da0ecd21-d35b-4533-8173-afeb81cdc6f3",
                discount_content = "스타벅스 카드 10% 할인",
                discount_type = "카드할인",
                is_brand_like = true
            ),
        )
    )

    val mockImmediateBrands = MutableLiveData(
        listOf(
            BenefitBrand(
                id = 1,
                name = "스타벅스",
                logo_img_url = "https://github.com/librarywon/Codelab_Compose/assets/52442547/da0ecd21-d35b-4533-8173-afeb81cdc6f3",
                discount_content = "스타벅스 카드 10% 할인",
                discount_type = "카드할인",
                is_brand_like = true
            ),
            BenefitBrand(
                id = 2,
                name = "스타벅스",
                logo_img_url = "https://github.com/librarywon/Codelab_Compose/assets/52442547/da0ecd21-d35b-4533-8173-afeb81cdc6f3",
                discount_content = "스타벅스 카드 10% 할인",
                discount_type = "카드할인",
                is_brand_like = true
            ),
            BenefitBrand(
                id = 3,
                name = "스타벅스",
                logo_img_url = "https://github.com/librarywon/Codelab_Compose/assets/52442547/da0ecd21-d35b-4533-8173-afeb81cdc6f3",
                discount_content = "스타벅스 카드 10% 할인",
                discount_type = "카드할인",
                is_brand_like = true
            ),
            BenefitBrand(
                id = 3,
                name = "스타벅스",
                logo_img_url = "https://github.com/librarywon/Codelab_Compose/assets/52442547/da0ecd21-d35b-4533-8173-afeb81cdc6f3",
                discount_content = "스타벅스 카드 10% 할인",
                discount_type = "카드할인",
                is_brand_like = true
            )
        )
    )
}