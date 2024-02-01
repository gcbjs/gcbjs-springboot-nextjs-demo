'use client'
import {Button, Input, Space} from "antd";

const { Search } = Input;

export default function TicketTableHeader() {


    return (
        <Space>
            <Search placeholder="输入关键词"  enterButton />
            <Button type="primary" >
                新增
            </Button>
        </Space>
    )
}