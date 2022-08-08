package com.da.retrofitexample.model

import com.google.gson.annotations.SerializedName
import org.koin.dsl.module.module

class Hero {

    @SerializedName("name")
    private var name: String? = null
    private var realname: String? = null
    private var team: String? = null
    private var firstappearance: String? = null
    private var createdby: String? = null
    private var publisher: String? = null
    private var imageurl: String? = null
    private var bio: String? = null

val TEXT = "text"
    fun Hero(
        name: String?,
        realname: String?,
        team: String?,
        firstappearance: String?,
        createdby: String?,
        publisher: String?,
        imageurl: String?,
        bio: String?
    ) {
        this.name = name
        this.realname = realname
        this.team = team
        this.firstappearance = firstappearance
        this.createdby = createdby
        this.publisher = publisher
        this.imageurl = imageurl
        this.bio = bio
    }

    fun getName(): String? {
        return name
    }

    fun getRealname(): String? {
        return realname
    }

    fun getTeam(): String? {
        return team
    }

    fun getFirstappearance(): String? {
        return firstappearance
    }

    fun getCreatedby(): String? {
        return createdby
    }

    fun getPublisher(): String? {
        return publisher
    }

    fun getImageurl(): String? {
        return imageurl
    }

    fun getBio(): String? {
        return bio
    }
}
//val heromodule = module { single {Hero() }}