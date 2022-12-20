package com.zoltanlorinczi.project_retrofit.api

import com.zoltanlorinczi.project_retrofit.api.model.*
import retrofit2.Response

class ThreeTrackerRepository {

    /**
     * 'suspend' keyword means that this function can be blocking.
     * We need to be aware that we can only call them from within a coroutine or an other suspend function!
     */
    suspend fun login(loginRequestBody: LoginRequestBody): Response<LoginResponse> {
        return RetrofitInstance.USER_API_SERVICE.login(loginRequestBody)
    }

    suspend fun getTasks(token: String): Response<List<TaskResponse>> {
        return RetrofitInstance.USER_API_SERVICE.getTasks(token)
    }

    suspend fun getMyUser(token: String): Response<GetMyUserResponse> {
        return RetrofitInstance.USER_API_SERVICE.getMyUser(token)
    }

    suspend fun forgetPassword(forgetPassBody: ForgetPasswordRequestBody): Response<ForgetPasswordResponse> {
        return RetrofitInstance.USER_API_SERVICE.forgetPassword(forgetPassBody)
    }

    suspend fun createTask(createTaskRequestBody: CreateTaskRequestBody): Response<String> {
        return RetrofitInstance.USER_API_SERVICE.createTask(createTaskRequestBody)
    }

}