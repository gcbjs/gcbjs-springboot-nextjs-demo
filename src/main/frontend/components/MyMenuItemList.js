import {BarChartOutlined, InsertRowAboveOutlined, UserOutlined} from "@ant-design/icons";
import React from "react";
import {Menu} from "antd";


const customItems = [
    {
        key: '/task-manage',
        icon: <BarChartOutlined/>,
        label: '工单看板'
    },
    {
        key: '/user-manage',
        icon: <UserOutlined/>,
        label: '用户管理'
    },
    {
        key: '/schedule-manage',
        icon: <InsertRowAboveOutlined/>,
        label: '排班管理'
    }
]

export default async function MyMenuItemList() {

    const sleep = ms => new Promise(r => setTimeout(r, ms));
    await sleep(2000);

    return (
        <Menu theme="dark" mode="inline" defaultSelectedKeys={['1']}>
            {customItems.map(item => (
                <Menu.Item key={item.key} icon={item.icon}>
                    {item.label}
                </Menu.Item>
            ))}
        </Menu>
    )
}