package com.master.kit.testcase.retrofit

import com.dks.master.masterretrofit.BaseBean
import com.dks.master.masterretrofit.IViewResponse

/**
 * Created by master on 2017/10/12 0012.
 */
interface IEResponse<T:BaseBean>:IViewResponse {
    fun onResponseSuccess(bean:T)
    fun onResponseFailOffSiteLogin(bean:T)
    fun onResponseFailSessionOverdue(bean:T)
    fun onResponseFailMessage(bean:T)
}