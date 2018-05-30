export default class Util {
  static addDays(date: Date, days: number): Date {
    let dat = new Date(date.valueOf());
    dat.setDate(dat.getDate() + days);
    return dat;
  }
}
