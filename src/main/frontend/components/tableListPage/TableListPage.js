import styled from "styled-components";
import {Card} from "antd";
import {produce} from 'immer';


const Root = styled(Card)`
  min-width: 1200px;
`;

const reducer = (
    state,
    action
) => {
    switch (action.type) {
        case 'SET_SELECT_OPTIONS': {
            return produce(state, (draft) => {
                Object.assign(draft.selectOptions, action.payload);
            });
        }
        case 'SET_QUERY': {
            return produce(state, (draft) => {
                draft.table.query = action.payload;
            });
        }
        case 'SET_PAGINATION': {
            return produce(state, (draft) => {
                const { pageIndex, pageSize } = action.payload;
                draft.table.pageIndex = pageIndex || draft.table.pageIndex;
                draft.table.pageSize = pageSize || draft.table.pageSize;
            });
        }
        case 'RELOAD_DATA': {
            return produce(state, (draft) => {
                draft.table.query = { ...draft.table.query };
            });
        }
        case 'LOAD_DATA': {
            return produce(state, (draft) => {
                draft.table.isLoading = true;
            });
        }
        case 'LOAD_DATA_SUCCESS': {
            return produce(state, (draft) => {
                draft.table.isLoading = false;
                draft.table.total = action.payload.total;
                draft.table.dataSource = action.payload.data;
            });
        }
        case 'LOAD_DATA_FAILED': {
            return produce(state, (draft) => {
                draft.table.isLoading = false;
            });
        }
        default: {
            return state;
        }
    }
};

export default function TableListPage({
    config
                                      }) {

}