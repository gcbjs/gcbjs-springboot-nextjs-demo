import {Empty, Table} from "antd";
import {getTicketPage} from "@/lib/server";


const columns = [
    {
        title: '工单编号',
        dataIndex: 'ticketId',
        key: 'ticketId',
    },
    {
        title: '工单状态',
        dataIndex: 'ticketStatusDesc',
        key: 'ticketStatusDesc',
    },
    {
        title: '派单时间',
        dataIndex: 'dispatchTimeDesc',
        key: 'dispatchTimeDesc',
    },
    {
        title: '创建时间',
        dataIndex: 'createTimeStr',
        key: 'createTimeStr',
    },
    {
        title: '完成时间',
        dataIndex: 'finishTimeStr',
        key: 'finishTimeStr',
    },
    {
        title: '操作',
        dataIndex: 'action',
        key: 'action',
    }
];

export default async function Page() {

    const data = await getTicketPage()
    const {items} = data

    if (!data || items.length === 0) {
        return <Empty />;
    }

    return (
        <Table dataSource={items} columns={columns} />
    )
}