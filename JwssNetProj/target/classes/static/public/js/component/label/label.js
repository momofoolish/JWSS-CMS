import React, { useState, Fragment } from 'react';
import './label.css';

// 标签组件
function Label(props) {
    // 组件状态
    const [tagArray, setTagArray] = useState([]);       //储存每个标签的value值
    // 组件事件
    const onKeyUp = (event) => {
        if (event.keyCode === 13) {
            var newArr = [...tagArray, (event.target.value + "").trim()];
            props.onChange(newArr);
            setTagArray(newArr);
            event.target.value = "";
        }
    }
    const onRemove = (index) => {
        var newArr = tagArray;
        newArr.splice(index, 1);
        props.onChange(newArr);
        setTagArray([...newArr]);
    }
    // 渲染组件
    return (
        <div className="com-public-label">
            {
                tagArray.map((item, index) => {
                    return <Tag key={index} value={item} index={index}
                        onRemove={onRemove}
                    />
                })
            }
            <Fragment>
                <input type="text" className="com-public-label-input"
                    onKeyUp={onKeyUp} />
                <label>按回车Enter创建标签</label>
            </Fragment>
        </div>
    )
}

// 成形的标签组件
function Tag(props) {
    // 组件事件
    const onClick = () => {
        props.onRemove(props.index);
    }
    // 渲染组件
    return <input type="button" value={props.value} className="com-public-label-button"
        onClick={onClick}
    />
}
