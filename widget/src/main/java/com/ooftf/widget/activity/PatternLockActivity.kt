package com.ooftf.widget.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.service.base.BaseSlidingActivity
import com.ooftf.widget.R

@Route(path = "/widget/activity/patternLock")
class PatternLockActivity : BaseSlidingActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pattern_lock)
    }
}
