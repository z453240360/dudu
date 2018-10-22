package com.dodo.marcket.utils;

import android.content.Context;
import android.graphics.Color;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.dodo.marcket.MainActivity;
import com.dodo.marcket.R;

import java.util.Calendar;

/**
 * Created by Administrator on 2018/3/30 0030.
 */

public class TimePickerHelper {

    public static TimePickerBuilder initTimePicker(Context context, OnTimeSelectListener listener, boolean isShowDay) {
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        //startDate.set(2013,1,1);
        Calendar endDate = Calendar.getInstance();
        //endDate.set(2020,1,1);

        //正确设置方式 原因：注意事项有说明
        startDate.set(2013, 0, 1);
        endDate.set(2020, 11, 31);

        TimePickerBuilder pickerBuilder = new TimePickerBuilder(context, listener);
        pickerBuilder.setType(new boolean[]{true, true, isShowDay, false, false, false})
                .setTitleColor(context.getResources().getColor(R.color.basicColor))//标题文字颜色
                .setSubmitColor(context.getResources().getColor(R.color.basicColor))//确定按钮文字颜色
                .setCancelColor(context.getResources().getColor(R.color.basicColor))//取消按钮文字颜色
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确定")//确认按钮文字
                .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                .setRangDate(startDate, endDate)//起始终止年月日设定
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(true)//是否循环滚动
                .setLabel("年", "月", "日", "时", "分", "秒")//默认设置为年月日时分秒
//                .setContentSize(18)//滚轮文字大小
                .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                .setRangDate(startDate, endDate)//起始终止年月日设定
                .setTitleText("请选择月份")//标题文字
                .build();

        return pickerBuilder;
    }

    public static OptionsPickerBuilder initDatePicker(Context context, OnOptionsSelectListener listener, boolean isShowDay) {
        OptionsPickerBuilder pvOptions = new OptionsPickerBuilder(context, listener);
        pvOptions.setSubmitText("确定")//确定按钮文字
                .setCancelText("取消")//取消按钮文字
                .setTitleText("城市选择")//标题
                .setSubCalSize(18)//确定和取消文字大小
                .setTitleSize(20)//标题文字大小
                .setTitleColor(Color.BLACK)//标题文字颜色
                .setSubmitColor(Color.BLUE)//确定按钮文字颜色
                .setCancelColor(Color.BLUE)//取消按钮文字颜色
                .setTitleBgColor(0xFF333333)//标题背景颜色 Night mode
                .setBgColor(0xFF000000)//滚轮背景颜色 Night mode
                .setContentTextSize(18)//滚轮文字大小
                .setLabels("省", "市", "区")//设置选择的三级单位
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setCyclic(false, false, false)//循环与否
                .setSelectOptions(1, 1, 1)  //设置默认选中项
                .setOutSideCancelable(false)//点击外部dismiss default true
                .isDialog(true)//是否显示为对话框样式
                .isRestoreItem(true)//切换时是否还原，设置默认选中第一项。
                .build();

//        pvOptions.setPicker(options1Items, options2Items, options3Items);//添加数据源
        return pvOptions;
    }

}
