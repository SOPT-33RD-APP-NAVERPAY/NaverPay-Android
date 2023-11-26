package com.dosopt.naverpay.ui.main.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dosopt.naverpay.domain.model.place.BrandList
import com.dosopt.naverpay.domain.model.place.NearbyplaceList
import com.dosopt.naverpay.domain.model.place.OnsitepaymentList
import com.dosopt.naverpay.domain.model.place.User

class PlaceViewModel : ViewModel() {
    val mockUser = MutableLiveData(
        User(
            "원희연"
        )
    )
    val mockNearbyplaceList = MutableLiveData(
        listOf(
            NearbyplaceList(
                id = 1,
                name = "cu 편의점",
                logo_img_url = "https://github.com/DO-SOPT-ANDROID/minhoe-heo/assets/145532333/08e7a512-ed0b-4197-b935-db471a684d2f",
                distance = 24
            ),
            NearbyplaceList(
                id = 2,
                name = "cu 편의점",
                logo_img_url = "https://github.com/DO-SOPT-ANDROID/minhoe-heo/assets/145532333/08e7a512-ed0b-4197-b935-db471a684d2f",
                distance = 24
            ),
            NearbyplaceList(
                id = 3,
                name = "cu 편의점",
                logo_img_url = "https://github.com/DO-SOPT-ANDROID/minhoe-heo/assets/145532333/08e7a512-ed0b-4197-b935-db471a684d2f",
                distance = 24
            ),
            NearbyplaceList(
                id = 4,
                name = "cu 편의점",
                logo_img_url = "https://github.com/DO-SOPT-ANDROID/minhoe-heo/assets/145532333/08e7a512-ed0b-4197-b935-db471a684d2f",
                distance = 24
            ),
            NearbyplaceList(
                id = 5,
                name = "cu 편의점",
                logo_img_url = "https://github.com/DO-SOPT-ANDROID/minhoe-heo/assets/145532333/08e7a512-ed0b-4197-b935-db471a684d2f",
                distance = 24
            ),
            NearbyplaceList(
                id = 6,
                name = "cu 편의점",
                logo_img_url = "https://github.com/DO-SOPT-ANDROID/minhoe-heo/assets/145532333/08e7a512-ed0b-4197-b935-db471a684d2f",
                distance = 24
            )
        )
    )
    val mockBrandList = MutableLiveData(
        listOf(
            BrandList(
                id = 1,
                logo_img_url = "https://github.com/DO-SOPT-ANDROID/minhoe-heo/assets/145532333/08e7a512-ed0b-4197-b935-db471a684d2f"
            ),
            BrandList(
                id = 2,
                logo_img_url = "https://github.com/DO-SOPT-ANDROID/minhoe-heo/assets/145532333/08e7a512-ed0b-4197-b935-db471a684d2f"
            ),
            BrandList(
                id = 3,
                logo_img_url = "https://github.com/DO-SOPT-ANDROID/minhoe-heo/assets/145532333/08e7a512-ed0b-4197-b935-db471a684d2f"
            ),
            BrandList(
                id = 4,
                logo_img_url = "https://github.com/DO-SOPT-ANDROID/minhoe-heo/assets/145532333/08e7a512-ed0b-4197-b935-db471a684d2f"
            ),
            BrandList(
                id = 5,
                logo_img_url = "https://github.com/DO-SOPT-ANDROID/minhoe-heo/assets/145532333/08e7a512-ed0b-4197-b935-db471a684d2f"
            ),
            BrandList(
                id = 6,
                logo_img_url = "https://github.com/DO-SOPT-ANDROID/minhoe-heo/assets/145532333/08e7a512-ed0b-4197-b935-db471a684d2f"
            )
        )
    )
    val mockOnsitepaymentList = MutableLiveData(
        listOf(
            OnsitepaymentList(
                id = 1,
                logo_img_url = "https://github.com/DO-SOPT-ANDROID/minhoe-heo/assets/145532333/08e7a512-ed0b-4197-b935-db471a684d2f",
                name = "도미노피자"
            ),
            OnsitepaymentList(
                id = 2,
                logo_img_url = "https://github.com/DO-SOPT-ANDROID/minhoe-heo/assets/145532333/08e7a512-ed0b-4197-b935-db471a684d2f",
                name = "도미노피자"
            ),
            OnsitepaymentList(
                id = 3,
                logo_img_url = "https://github.com/DO-SOPT-ANDROID/minhoe-heo/assets/145532333/08e7a512-ed0b-4197-b935-db471a684d2f",
                name = "도미노피자"
            ),
            OnsitepaymentList(
                id = 4,
                logo_img_url = "https://github.com/DO-SOPT-ANDROID/minhoe-heo/assets/145532333/08e7a512-ed0b-4197-b935-db471a684d2f",
                name = "도미노피자"
            ),
            OnsitepaymentList(
                id = 5,
                logo_img_url = "https://github.com/DO-SOPT-ANDROID/minhoe-heo/assets/145532333/08e7a512-ed0b-4197-b935-db471a684d2f",
                name = "도미노피자"
            ),
            OnsitepaymentList(
                id = 6,
                logo_img_url = "https://github.com/DO-SOPT-ANDROID/minhoe-heo/assets/145532333/08e7a512-ed0b-4197-b935-db471a684d2f",
                name = "도미노피자"
            )
        )
    )
}

