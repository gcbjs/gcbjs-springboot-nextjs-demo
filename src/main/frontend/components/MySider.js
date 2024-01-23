'use client';
import {Image, Layout} from "antd";
import React from "react";
import Link from "next/link";
import MyMenuItemList from "@/components/MyMenuItemList";

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
                <MyMenuItemList/>
            </nav>
        </Sider>
    )
}