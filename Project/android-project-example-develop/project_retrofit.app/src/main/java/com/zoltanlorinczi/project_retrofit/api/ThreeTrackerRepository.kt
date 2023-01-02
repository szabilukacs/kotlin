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

    suspend fun createTask(
        token: String,
        createTaskRequestBody: CreateTaskRequestBody
    ): Response<CreateTaskResponse> {
        return RetrofitInstance.USER_API_SERVICE.createTask(token, createTaskRequestBody)
    }

    suspend fun getDepartments(token: String): Response<List<GetDepartmentsResponse>> {
        return RetrofitInstance.USER_API_SERVICE.getDepartments(token)
    }

    suspend fun getActivities(token: String): Response<List<GetActivitiesResponse>> {
        return RetrofitInstance.USER_API_SERVICE.getActivities(token)
    }

    suspend fun updateProfile(
        token: String,
        updateProfileRequestBody: UpdateProfileRequestBody
    ): Response<UpdateProfileResponse> {
        return RetrofitInstance.USER_API_SERVICE.updateProfile(token, updateProfileRequestBody)
    }

}