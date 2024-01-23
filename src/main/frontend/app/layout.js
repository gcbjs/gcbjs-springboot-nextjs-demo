import React from 'react';
import {AntdRegistry} from "@ant-design/nextjs-registry";
import {Layout, Menu, theme} from "antd";
import MyFooter from "@/components/MyFooter";
import MyContent from "@/components/MyContent";
import MyHeader from "@/components/MyHeader";
import MySider from "@/components/MySider";

const layoutStyle = {
    display: 'flex',
    width: '100%',
    minHeight: '100%',
};

export default function RootLayout({children}) {
    return (
        <html lang="en">
        <body>
        <AntdRegistry>
            <Layout >
                <MySider/>
                <Layout >
                    <MyHeader/>
                    <MyContent>{children}</MyContent>
                    <MyFooter/>
                </Layout>
            </Layout>
        </AntdRegistry>
        </body>
        </html>
    );
}

