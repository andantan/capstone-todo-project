import React from "react";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import { ko } from "date-fns/locale";
import { useRecoilState } from 'recoil';
import { selectedDateState } from '../../Recoil/Atoms/dateatom.js';
import "./calendar.css";

const Calendar = ({ currentMonth, onMonthChange }) => {
  const [selectedDate, setSelectedDate] = useRecoilState(selectedDateState);

  const today = new Date();

  if (!selectedDate) {
    setSelectedDate(today);
  }

  // 날짜 변경 시 상태 업데이트 및 월 변경 여부 확인 후 부모 컴포넌트에 전달
  const handleDateChange = (date) => {
    setSelectedDate(date);
    const newMonthDate = new Date(date.getFullYear(), date.getMonth(), 1);
    onMonthChange?.(newMonthDate);
  };

  return (
    <div className="calendar-container">
      <DatePicker
        selected={selectedDate}
        onChange={handleDateChange}
        inline
        locale={ko}
        openToDate={currentMonth}
        renderCustomHeader={() => null}
        formatWeekDay={(nameOfDay) => nameOfDay.substring(0, 1)}
        showMonthDropdown
        showYearDropdown
        dropdownMode="select"
      />
    </div>
  );
};

export default Calendar;
