'use client';
import {Image, Menu} from "antd";
import React, {Suspense} from "react";
import {BarChartOutlined, UserOutlined, InsertRowAboveOutlined} from "@ant-design/icons";
import {Layout} from "antd";
import Link from "next/link";
import MyMenuSkeleton from "@/components/MyMenuSkeleton";

const {Sider} = Layout;


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
            <Link href={'/'}>
                <section>
                    <Image
                        width={100}
                        src="https://gw.alipayobjects.com/zos/antfincdn/aPkFc8Sj7n/method-draw-image.svg"
                        preview={false}
                    />
                </section>
            </Link>
            <nav>

            </nav>
        </Sider>
    )
}