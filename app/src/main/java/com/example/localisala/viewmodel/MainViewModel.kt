package com.example.localisala.viewmodel

import android.location.Location
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.localisala.model.Course
import com.example.localisala.model.Period
import com.example.localisala.model.Subject
import com.example.localisala.model.University
import com.example.localisala.repository.MainRepository
import kotlinx.coroutines.launch

class MainViewModel(val repository: MainRepository): ViewModel(),
    android.widget.SearchView.OnQueryTextListener {

    private val _universities = MutableLiveData<List<University>>()
    val universities : LiveData<List<University>> get() = _universities

    private val _courses = MutableLiveData<List<Course>>()
    val courses: LiveData<List<Course>> get() = _courses

    private val _periodes= MutableLiveData<List<Period>>()
    val periodes: LiveData<List<Period>> get() = _periodes

    private val _subjects = MutableLiveData<List<Subject>>()
    val subjects: LiveData<List<Subject>> get() = _subjects

    private val _filteredUniversities = MutableLiveData<List<University>>()
    val filteredUniversities: LiveData<List<University>> get() = _filteredUniversities


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

    fun fetchPeriodData(universityIdDocument: String, courseIdDocument: String){
        viewModelScope.launch {
            val periodList = repository.getPeriod(universityIdDocument, courseIdDocument)
            _periodes.postValue(periodList)

        }
    }

    fun fetchSubjectData(universityIdDocument: String,
                         courseIdDocument: String,
                         periodIdDocument: String
    ){
        viewModelScope.launch {
            val subjectList = repository.getSubject(
                universityIdDocument,
                courseIdDocument,
                periodIdDocument
            )
            _subjects.postValue(subjectList)
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        val query = newText.orEmpty().trim()
        _filteredUniversities.value = _universities.value?.filter {
            it.name.contains(query, ignoreCase = true)
        }
        return true
    }


}