package com.example.shapesapp.presenter
import com.example.shapesapp.interactor.ShapesInteractor
import com.example.shapesapp.models.Shape
import java.io.Serializable

class StatsPresenter {

    val countByGroup: Serializable
        get() = ShapesInteractor.instance.countByGroup
}
