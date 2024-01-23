'use client';
import React from "react";
import {Layout} from "antd";
import dayjs from "dayjs";
const {Footer} = Layout;

export default function MyFooter() {

    return (
        <Footer
            style={{
                textAlign: 'center',
            }}
        >
            Ant Design ©{dayjs(new Date()).format('YYYY')} Created by Ant UED
        </Footer>
    )
}