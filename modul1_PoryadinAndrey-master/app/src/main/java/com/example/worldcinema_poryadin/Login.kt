package com.example.worldcinema_poryadin
data class ApiResponse(
    val success: Boolean,
    val user: User,
    val facility: Facility,
    val payload: Payload,
)

data class UserResponse(val success: Boolean, val user: User?)

data class PayLoadResponse(val success: Boolean,val payload: Payload?)

data class FriendsResponse(val success: Boolean, val friends:List<Friend>)

data class PostMessageRequest(
    val userId: String,
    val urlMedia: String,
    val content: String
)
data class Friend(
    val id:String,
    val name:String,
    val score: Double
)

data class EventResponse(

    val success: Boolean,
    val events: List<Event>
)

data class User(
    val username: String,
    val photo: String
)
data class Event(
    val id: String,
    val name: String,
    val eventDate: String,
    val description: String,
    val urlMedia: String
)

data class Facility(
    val name: String,
    val mainPhoto: String
)

data class Payload(
    val rank: Double,
    val score: Double,
    val league: League,
    val coins: Double,
    val diamonds: Double,
    val medals: List<Medal>,
    val speed: Speed,
    val accuracy: Accuracy,
    val achievements: List<Achievement>,
    val goals: List<Goal>,
    val challenges: List<Challenge>
)

data class League(
    val name: String,
    val imgLeague: String
)

data class Speed(
    val best: Double,
    val monthAvg: Double,
    val avg: Double
)

data class Accuracy(
    val data: Map<String, Any>?
)

data class Medal(
    val id: String,
    val name: String,
    val imgUri: String?
)

data class Achievement(
    val id: String,
    val name: String,
    val caption: String,
    val img: String,
    val progress: Double,
    val status: Boolean,
    val points: Int
)

data class Goal(
    val id: String,
    val name: String,
    val imgUri: String?,
    val progress: Double
)

data class Challenge(
    val id: String,
    val name: String,
    val img: String
)