'use client'
import {Card, List} from "antd";
import {getUserIdsByTargetDate} from "@/lib/server";
import dayjs from "dayjs";



export default async function OnDutyList() {

    const data = await getUserIdsByTargetDate(dayjs(new Date()).format("YYYY-MM-DD"));

    return (
       <>
           <Card title="今日值班人员" bordered={true}>
           <List
               size="small"
               dataSource={data}
               renderItem={(user,index) => (
                       <List.Item>
                           <List.Item.Meta
                               title={user.name}
                               description={user.mobile} />
                       </List.Item>
                   )
               }
           />
           </Card>
       </>
    )
}