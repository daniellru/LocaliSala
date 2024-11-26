package com.example.localisala.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.localisala.model.Course
import com.example.localisala.model.University
import com.example.localisala.repository.MainRepository
import kotlinx.coroutines.launch

class MainViewModel(val repository: MainRepository): ViewModel(){

    private val _universities = MutableLiveData<List<University>>()
    val universities : LiveData<List<University>> get() = _universities

    private val _courses = MutableLiveData<List<Course>>()
    val courses: LiveData<List<Course>> get() = _courses

    fun fetchUniversityData() {
        viewModelScope.launch {
            val universitiesList = repository.getUniversities()
            _universities.postValue(universitiesList)
        }
    }

    fun fetchCourseData(idDocument: String){
        viewModelScope.launch {
            val courseList = repository.getCourses(idDocument)
            _courses.postValue(courseList)
        }
    }

}