export default function validate(beginDate, endDate) {
  if (beginDate == null || endDate == null) return true;

  const arrStart = beginDate.split('-');
  const arrEnd = endDate.split('-');

  for (let i = 0; i < 3; i += 1) {
    const start = parseInt(arrStart[i], 10);
    const end = parseInt(arrEnd[i], 10);
    if (start > end) {
      return false;
    } else if (start < end) {
      break;
    }
  }

  return true;
}
