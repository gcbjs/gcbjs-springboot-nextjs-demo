'use client';
import React from "react";
import {Layout, theme} from "antd";

const { Content} = Layout;

export default function MyContent({children}) {
    const {
        token: {colorBgContainer, borderRadiusLG}
    } = theme.useToken();
    return (
        <Content
            style={{
                margin: '24px 16px 0',
            }}
        >
            <div
                style={{
                    padding: 24,
                    minHeight:'100vh',
                    background: colorBgContainer,
                    borderRadius: borderRadiusLG,
                }}
            >
                {children}
            </div>
        </Content>
    )
}