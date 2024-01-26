import React from "react";
import {Card, Statistic} from "antd";
import {countTodayTicket} from "@/lib/server";
import dayjs from "dayjs";


export default async function TodayWaitDo() {

    const data = await countTodayTicket(dayjs(new Date()).format('YYYY-MM-DD'));
    return (
        <>
            <Card title="今日待办工单" bordered={true}>
                <Statistic
                    value={data}
                    // precision={2}
                    valueStyle={{
                        color: '#cf1322',
                    }}
                    // prefix={<ArrowDownOutlined />}
                    // suffix="个"
                />
            </Card>
        </>
    )
}