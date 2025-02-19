package com.adanalis.myfirstapplication

import java.io.Serializable


class ModelUser(
    var name:String,
    var password:String,
    var id:String
):Serializable {
}