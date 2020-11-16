package ru.catcherry.lab1;

import com.google.gson.annotations.SerializedName;

public class FactDetail {
    @SerializedName("_id")
    String _id;

    @SerializedName("text")
    String text;

    @SerializedName("type")
    String type;

    @SerializedName("upvotes")
    String upvotes;



//    "_id": "591d9b2f227c1a0020d26823",
//            "text": "Every year, nearly four million cats are eaten in China as a delicacy.",
//            "type": "cat",
//            "user": {
//        "_id": "5a9ac18c7478810ea6c06381",
//                "name": {
//            "first": "Alex",
//                    "last": "Wohlbruck"
//        }
//    },
//            "upvotes": 12,
//            "userUpvoted": null
}
