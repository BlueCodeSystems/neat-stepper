package com.nerdstone.neatandroidstepper.sample

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.nerdstone.neatandroidstepper.core.domain.StepperActions
import com.nerdstone.neatandroidstepper.core.model.StepModel
import com.nerdstone.neatandroidstepper.core.stepper.Step
import com.nerdstone.neatandroidstepper.core.stepper.StepVerificationState
import com.nerdstone.neatandroidstepper.core.stepper.StepperPagerAdapter
import com.nerdstone.neatandroidstepper.core.widget.NeatStepperLayout

class MainActivity : FragmentActivity(), StepperActions {

    lateinit var neatStepperLayout: NeatStepperLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        neatStepperLayout = findViewById(R.id.neatStepperLayout)
        neatStepperLayout.stepperActions = this
        neatStepperLayout.setUpViewWithAdapter(
            StepperPagerAdapter(
                supportFragmentManager,
                lifecycle,
                mutableListOf(
                    StepOneFragment(
                        StepModel.Builder()
                            .title("Step Sample")
                            .subTitle("One")
                            .bottomNavigationColorResource(R.color.colorBlack)
                            .build()
                    ),
                    StepOneFragment(
                        StepModel.Builder()
                            .title("Step Sample").subTitle("Two")
                            .bottomNavigationColorResource(R.color.colorGreen)
                            .build()
                    ),
                    StepOneFragment(
                        StepModel.Builder()
                            .title("Step Sample")
                            .subTitle("Three")
                            .bottomNavigationColorResource(R.color.colorPink)
                            .build()
                    )
                )
            )
        )
        neatStepperLayout.showLoadingIndicators(false)
    }

    override fun onStepError(stepVerificationState: StepVerificationState) {
    }

    override fun onButtonNextClick(step: Step) {
    }

    override fun onButtonPreviousClick(step: Step) {
    }

    override fun onStepComplete(step: Step) {
        Toast.makeText(this, "Stepper completed", Toast.LENGTH_SHORT).show()
    }

    override fun onExitStepper() {
        val confirmCloseDialog = AlertDialog.Builder(this)
        confirmCloseDialog.apply {
            setTitle("Confirm close")
            setMessage("All the unsaved data will get lost if you quit")
            setPositiveButton("Exit") { _, _ -> finish() }
            setNegativeButton("Cancel") { _, _ -> return@setNegativeButton }
            create()
        }
        confirmCloseDialog.show()
    }

    override fun onCompleteStepper() {
        Toast.makeText(this, "Completed entire step", Toast.LENGTH_SHORT).show()
    }
}
