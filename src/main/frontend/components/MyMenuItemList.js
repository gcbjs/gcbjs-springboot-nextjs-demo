'use client';
import {BarChartOutlined, InsertRowAboveOutlined, UserOutlined} from "@ant-design/icons";
import React from "react";
import {Menu} from "antd";
import {useRouter} from "next/navigation";


const customItems = [
    {
        key: '/ticket',
        icon: <BarChartOutlined/>,
        label: '工单管理'
    },
    {
        key: '/user',
        icon: <UserOutlined/>,
        label: '用户管理'
    },
    {
        key: '/schedule',
        icon: <InsertRowAboveOutlined/>,
        label: '排班管理'
    }
]

export default function MyMenuItemList() {

    const router = useRouter();


    const handleClick = (e) => {
        router.push(e.key);
    };

    return (
        <Menu theme="dark" mode="inline"
              defaultSelectedKeys={['1']}
              items={customItems} onClick={handleClick}/>
    )
}