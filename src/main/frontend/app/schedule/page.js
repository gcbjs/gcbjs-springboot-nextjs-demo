'use client';
import React from 'react';
import {Badge, Calendar, Tag} from "antd";
import dayjs from "dayjs";

const scheduleData = [
    {
        name: '小小鱼',
        scheduleDate: '2024-01-08',
        scheduleDesc: '全天'
    },
    {
        name: '张三',
        scheduleDate: '2024-01-08',
        scheduleDesc: '全天'
    },
    {
        name: '李四',
        scheduleDate: '2024-01-16',
        scheduleDesc: '全天'
    },
    {
        name: '王五',
        scheduleDate: '2024-01-28',
        scheduleDesc: '全天'
    },
    {
        name: '朱六',
        scheduleDate: '2024-01-28',
        scheduleDesc: '全天'
    }
]


const getListData = (value) => {
    console.log(dayjs(value).format("YYYY-MM-DD"));
    return scheduleData.map(v => {
        if (v.scheduleDate === dayjs(value).format("YYYY-MM-DD")) {
            return v;
        }else {
            return null
        }
    })
};

const getMonthData = (value) => {
    if (value.month() === 8) {
        return 1394;
    }
};

export default function Page() {

    const onPanelChange = (value, mode) => {
        console.log(value.format('YYYY-MM-DD'), mode);
    };

    const onSelect = (date, info) => {
        console.log(date.format('YYYY-MM-DD'), info)
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
                                <span>{item.name}</span><Tag color="green">{item.scheduleDesc}</Tag>
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

    return (
        <Calendar onPanelChange={onPanelChange} onSelect={onSelect} cellRender={cellRender}/>
    )
}