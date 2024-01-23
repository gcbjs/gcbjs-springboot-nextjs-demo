'use client';
import {Menu} from "antd";
import React from "react";
import {UploadOutlined, UserOutlined, VideoCameraOutlined} from "@ant-design/icons";
import {Layout} from "antd";
const { Sider} = Layout;

const items = [UserOutlined, VideoCameraOutlined, UploadOutlined, UserOutlined].map(
    (icon, index) => ({
        key: String(index + 1),
        icon: React.createElement(icon),
        label: `nav ${index + 1}`,
    }),
);

export default function MySider() {
    return (
        <Sider
            breakpoint="lg"
            collapsedWidth="0"
            onBreakpoint={(broken) => {
                console.log(broken)
            }}
            onCollapse={(collapsed, type) => {
                console.log(collapsed, type);
            }}
        >
            <div className="demo-logo-vertical"/>
            <Menu theme="dark" mode="inline" defaultSelectedKeys={['4']} items={items}/>
        </Sider>
    )
}