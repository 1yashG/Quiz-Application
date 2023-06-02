package com.example.quizapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.widget.Toast
import com.example.quizapplication.databinding.ActivityLoginBinding
import com.example.quizapplication.databinding.ActivityQuizBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class QuizActivity() : AppCompatActivity(), Parcelable {
    private lateinit var binding: ActivityQuizBinding
    private lateinit var list:ArrayList<QuestionModel>
    private var count:Int=0
    private var score = 0

    constructor(parcel: Parcel) : this() {
        count = parcel.readInt()
        score = parcel.readInt()
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)
list=ArrayList<QuestionModel>()
Firebase.firestore.collection("quiz")
    .get().addOnSuccessListener{
    doct->
        list.clear()
    for(i in doct.documents){

 val QuestionModel=i.toObject(QuestionModel::class.java)
        list.add(QuestionModel!!)
    }
        binding.question.setText(list.get(0).question)
        binding.option1.setText(list.get(0).option1)
        binding.option2.setText(list.get(0).option2)
        binding.option3.setText(list.get(0).option3)
        binding.option4.setText(list.get(0).option4)
    }
     /*   list.add(QuestionModel("who is the father of Harry Potter","James Potter" ,"Hagrid","Lupin", "Dumbledore", "James Potter" ))
        list.add(QuestionModel("who is the father of Harry Potter","Lupin" ,"Hagrid","James Potter", "Dumbledore", "James Potter" ))
        list.add(QuestionModel("who is the father of Harry Potter","James Potter" ,"Hagrid","Lupin", "Dumbledore", "James Potter" ))
        list.add(QuestionModel("who is the father of Harry Potter","Lupin" ,"Hagrid","James Potter", "Dumbledore", "James Potter" ))
        list.add(QuestionModel("who is the father of Harry Potter","James Potter" ,"Hagrid","Lupin", "Dumbledore", "James Potter" ))*/



        binding.option1.setOnClickListener {
        nextData(binding.option1.text.toString())
}
        binding.option2.setOnClickListener {
            nextData(binding.option2.text.toString())
}
        binding.option3.setOnClickListener {
            nextData(binding.option3.text.toString())
}
        binding.option4.setOnClickListener {
            nextData(binding.option4.text.toString())
}

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(count)
        parcel.writeInt(score)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<QuizActivity> {
        override fun createFromParcel(parcel: Parcel): QuizActivity {
            return QuizActivity(parcel)
        }

        override fun newArray(size: Int): Array<QuizActivity?> {
            return arrayOfNulls(size)
        }
    }

    private fun nextData(i: String) {
if(count<list.size){
    if(list.get(count).ans.equals (i)) {score ++
    }
}

      count++
        if(count>=list.size)
        {
            val intent= Intent(this,ScoreActivity::class.java)
            intent.putExtra("Score",score)
           startActivity(intent)
            finish()
        }
        else{
        binding.question.setText(list.get(count).question)
        binding.option1.setText(list.get(count).option1)
        binding.option2.setText(list.get(count).option2)
        binding.option3.setText(list.get(count).option3)
        binding.option4.setText(list.get(count).option4)
    }
}
}