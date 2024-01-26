'use client';
import React, {useEffect, useState} from 'react';
import {Button, Calendar, Tag, theme} from "antd";
import dayjs from "dayjs";
import locale from 'antd/es/date-picker/locale/zh_CN';
import 'dayjs/locale/zh-cn';
import {scheduleList} from "@/lib/server";



export default function Page() {

    const { token } = theme.useToken();

    const [data,setData] = useState([])

    const fetchData = async () =>{
        const data = await scheduleList(null);
        setData(data)
    }

    useEffect(() =>{
        fetchData()
    },[])

    const onPanelChange = (value, mode) => {
        console.log("onPanelChange",value.format('YYYY-MM-DD'), mode);
    };

    const onSelect = (date, info) => {
        console.log("onSelect",date.format('YYYY-MM-DD'), info)
    }

    const monthCellRender = (value) => {
        const num = getMonthData(value);
        return num ? (
            <div className="notes-month">
                <section>{num}</section>
                <span>Backlog number</span>
            </div>
        ) : null;
    };

    const dateCellRender = (value) => {
        const listData = getListData(value);
        return (
            <ul className="events">
                {listData.map((item,index) => {
                    if (item === null) {
                        return null
                    }else {
                        return (
                            <li key={index} style={{
                                "listStyle": "none"
                            }}>
                                <Tag color="green">{item.name}</Tag>
                            </li>
                        )
                    }
                })}
            </ul>
        );
    };
    const cellRender = (current, info) => {
        if (info.type === 'date') return dateCellRender(current);
        if (info.type === 'month') return monthCellRender(current);
        return info.originNode;
    };


    const getListData = (value) => {
        return data.map(v => {
            if (v.scheduleDateStr === dayjs(value).format("YYYY-MM-DD")) {
                return v;
            }else {
                return null
            }
        })
    };

    const getMonthData = (value) => {
        return null
    };

    return (
        <div>
            <Calendar locale={locale} cellRender={cellRender} onSelect={onSelect} onPanelChange={onPanelChange}/>
        </div>

    )
}