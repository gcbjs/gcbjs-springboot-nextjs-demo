import dayjs from "dayjs";
import {Card, Statistic} from "antd";
import React from "react";
import {getTicketStatusCountForCurrentWeek} from "@/lib/server";


export default async function CountWeekTicket() {

    const data = await getTicketStatusCountForCurrentWeek();
    const total = data['TOTAL']
    const handled = data['HANDLED']
    const suffix = "/" + total+"(总)";
    return (
        <>
            <Card title="本周完成工单数" bordered={true}>
                <Statistic value={handled} suffix={suffix}/>
            </Card>
        </>
    )
}