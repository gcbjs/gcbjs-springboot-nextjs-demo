import React from "react";
import {Card, Col, Row, Statistic} from "antd";
import TicketOverview from "@/components/TicketOverview";
import OnDutyList from "@/components/OnDutyList";
import TodayWaitDo from "@/components/TodayWaitDo";
import CountWeekTicket from "../components/CountWeekTicket";

export default async function Home() {

    return (
        <>
            <Row gutter={16}>
                <Col span={8}>
                    <TodayWaitDo/>
                </Col>
                <Col span={8}>
                    <Card title="本周工单平均处理时间" bordered={true}>
                        <Statistic value="1小时6分"/>
                    </Card>
                </Col>
                <Col span={8}>
                    <CountWeekTicket/>
                </Col>
            </Row>
            <Row gutter={16} style={{
                marginTop: 16
            }}>
                <Col span={16}>
                    <Card title="工单总览" bordered={true}>
                        <TicketOverview/>
                    </Card>
                </Col>
                <Col span={8}>
                    <OnDutyList/>
                </Col>
            </Row>
        </>
    )

}
