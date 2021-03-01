package com.rana.mvvmmodelkotlin.util

import java.io.IOException

class ApiException(message:String):IOException(message)
class  NoInterNetException(message:String):IOException(message)
